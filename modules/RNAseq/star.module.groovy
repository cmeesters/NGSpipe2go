//rule for task STAR from catalog RNAseq
//desc: Align single- or paired-end RNAseq reads
STAR = {
    doc title: "STAR alignment",
        desc:  "Align single/paired end reads",
        constraints: "Paired end reads expected to have .R1 and .R2 suffix.",
        bpipe_version: "tested with bpipe 0.9.9",
        author: "Sergi Sayols"

    output.dir = MAPPED

    // create the TMP folder if it doesn't exists
    F_TMP = new File(TMP)
    if(! F_TMP.exists()) { 
        F_TMP.mkdirs()
    }
    // create the LOGS/STAR folder if it doesn't exists
    F_LOG = new File(LOGS + "/STAR")
    if(! F_LOG.exists()) {
        F_LOG.mkdirs()
    }
    
    // calculate name of the sample being processed (added paired end support)
    def OUTPUTFILE = input1
    int path_index = OUTPUTFILE.lastIndexOf("/")
    OUTPUTFILE = OUTPUTFILE.substring(path_index+1)

    if(ESSENTIAL_PAIRED == "yes") {
        OUTPUTFILE = (OUTPUTFILE =~ /.R1.fastq.gz/).replaceFirst("")
    } else {
        OUTPUTFILE = (OUTPUTFILE =~ /.fastq.gz/).replaceFirst("")
    }

    // star flags
    def STAR_FLAGS = "--runMode alignReads "        +
                     "--genomeLoad NoSharedMemory " +
                     "--outStd SAM "                +
                     "--outSAMattributes Standard " +
                     "--outSJfilterReads Unique "   +
                     "--readFilesCommand zcat "     +
                     "--outFileNamePrefix " + LOGS + "/STAR/" + OUTPUTFILE + " " +
                     "--outTmpDir " + TMP + "/" + OUTPUTFILE + " " +
                     STAR_UNMAPPED_BAM + " " +
                     STAR_REF      + " " +
                     STAR_THREADS  + " " +
                     STAR_MM       + " " +
                     STAR_OVERHANG + " " +
                     STAR_GTF      + " " +
                     STAR_EXTRA

    // samtools flags
    def SAMTOOLS_VIEW_FLAGS = "-bhSu " + STAR_SAMTOOLS_THREADS
    if(STAR_FILTER_SEC == "YES") {
        SAMTOOLS_VIEW_FLAGS = " -F 256 " + SAMTOOLS_VIEW_FLAGS    // remove secondary alignments (256 or 0x100)
    }

    def SAMTOOLS_SORT_FLAGS = " -O bam " + STAR_SAMTOOLS_THREADS

    // code chunk
    // TODO: warn if the genome index was created using another version of STAR?
    produce(OUTPUTFILE + ".bam", OUTPUTFILE + "Log.final.out") {
        exec """
            ${PREPARE_STAR} && 
            ${PREPARE_SAMTOOLS} &&
            
            if [ -e $TMP/$OUTPUTFILE ];
            then
                echo 'removing old STAR tmp folder';
                rm -r $TMP/$OUTPUTFILE*;
            fi &&
            
            ${RUN_STAR} $STAR_FLAGS --readFilesIn $inputs | ${RUN_SAMTOOLS} view $SAMTOOLS_VIEW_FLAGS - | ${RUN_SAMTOOLS} sort $SAMTOOLS_SORT_FLAGS -T ${TMP}/${OUTPUTFILE}_sort - > $output1 &&
            
            mv ${LOGS}/STAR/${OUTPUTFILE}SJ.out.tab $output.dir &&
            ln -s ${LOGS}/STAR/${OUTPUTFILE}Log.final.out $output.dir
        ""","STAR"
    }
}
