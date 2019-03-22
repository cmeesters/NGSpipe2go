//rule for task bowtie_se from catalog ChIPseq, version 1
//desc: Align single end reads
bowtie_se = {
    doc title: "Bowtie SE alignment",
        desc:  "Align single end reads",
        constraints: "Only works with compressed input. Samtools multithreaded version expected (>=1.2).",
        bpipe_version: "tested with bpipe 0.9.8.7",
        author: "Sergi Sayols"

    output.dir = MAPPED

    def BOWTIE_FLAGS = "-q --sam "  +
                       BOWTIE_QUALS    + " " + 
                       BOWTIE_BEST     + " " + 
                       BOWTIE_MM_SEED  + " " + 
                       BOWTIE_INSERT   + " " + 
                       BOWTIE_MAQERR   + " " + 
                       BOWTIE_MULTIMAP + " " + 
                       BOWTIE_THREADS  + " " + 
                       BOWTIE_EXTRA

    def SAMTOOLS_VIEW_FLAGS = "-bhSu "
    def SAMTOOLS_SORT_FLAGS = "-O bam " + BOWTIE_SAMTOOLS_THREADS

    transform(".fastq.gz") to (".bam") {
        exec """
            ${PREPARE_BOWTIE} &&
            ${PREPARE_SAMTOOLS} &&

            if [ ! -d ${TMP} ]; then
                     mkdir -p ${TMP};
            fi &&

            zcat $input | ${RUN_BOWTIE} $BOWTIE_FLAGS $BOWTIE_REF - | ${RUN_SAMTOOLS} view $SAMTOOLS_VIEW_FLAGS - | ${RUN_SAMTOOLS} sort $SAMTOOLS_SORT_FLAGS -T $TMP/\$(basename $output.prefix)_bowtie1_sort - > $output
        ""","bowtie_se"
    }
}

