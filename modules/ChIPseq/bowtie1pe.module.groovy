//rule for task bowtie_se from catalog ChIPseq, version 1
//desc: Align single end reads
bowtie_pe = {
    doc title: "Bowtie PE alignment",
        desc:  "Align paired end reads",
        constraints: "Only works with compressed input. Samtools multithreaded version expected (>=0.1.19).",
        bpipe_version: "tested with bpipe 0.9.8.7",
        author: "Sergi Sayols modified for paired end by Nastasja Kreim"

    output.dir = BOWTIE_MAPPED
    def OUTPUTFILE = input1
    int path_index = OUTPUTFILE.lastIndexOf("/")
    OUTPUTFILE = OUTPUTFILE.substring(path_index+1)
    println(OUTPUTFILE)
    OUTPUTFILE = (OUTPUTFILE =~ /.R1.fastq.gz/).replaceFirst("")

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

    produce(OUTPUTFILE + ".bam") {
        exec """
            ${PREPARE_BOWTIE}     &&
            ${PREPARE_SAMTOOLS} &&

            if [ -n "\$SLURM_JOBID" ]; then
              export TMPDIR=/jobdir/\${SLURM_JOBID};
            fi                                       &&

      ${RUN_BOWTIE} $BOWTIE_FLAGS $BOWTIE_REF -1 <(zcat $input1) -2 <(zcat $input2) | ${RUN_SAMTOOLS} view $SAMTOOLS_VIEW_FLAGS - | ${RUN_SAMTOOLS} sort $SAMTOOLS_SORT_FLAGS -T $TMPDIR/\$(basename $output.prefix) - > $output;
    ""","bowtie_pe"
  }
}

