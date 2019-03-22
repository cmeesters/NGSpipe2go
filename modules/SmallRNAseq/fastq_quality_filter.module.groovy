FastQQualityFilter = {
    doc title: "Remove sequences",
        desc:  "filter reads containing low-quality (Phred score below 20) bases in order to facilitate the PCR duplicates removal.",
        constraints: "Only supports compressed FASTQ files",
        author: "Antonio Domingues"

    output.dir = FASTQ_QUALITY_FILTER_OUTDIR
    def EXP = input.split("/")[-1].replaceAll(".cutadapt.fastq.gz", "")

    FASTQ_QUALITY_FILTER_FLAGS = " -q "   + MIN_QUAL  +
                                 " -p "   + MIN_PERCENT  +
                                 " -Q " + QUAL_FORMAT +
                                 FASTQ_QUALITY_FILTER_OTHER

    transform(".cutadapt.fastq.gz") to (".highQ.fastq.gz") {


        exec """
            ${PREPARE_FASTX} &&

            zcat $input | ${RUN_FASTQ_QUALITY_FILTER} $FASTQ_QUALITY_FILTER_FLAGS -o $output 2>&1 >> ${FASTQ_QUALITY_FILTER_OUTDIR}/${EXP}.fastq_quality_filter.log
            
      ""","FastQQualityFilter"
   }
}
