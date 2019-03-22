//rule for task phantompeak from catalog ChIPseq, version 1
//desc: Phantompeak
phantompeak = {
    doc title: "Phantompeak QC  plot",
        desc:  "Phantompeak",
        constraints: "",
        bpipe_version: "tested with bpipe 0.9.8.7",
        author: "Sergi Sayols"

    output.dir = QC + "/phantompeak"

    def PHANTOMPEAK_FLAGS = PHANTOMPEAK_MINSHIFT + " " + // left 'x' coordinate in plot
                            PHANTOMPEAK_MAXSHIFT + " " + // right 'x' coordinate in plot
                            PHANTOMPEAK_BINSIZE  + " " + // stepsize for cc calculation
                            PHANTOMPEAK_READLEN  + " " + // read length
                            PHANTOMPEAK_THREADS  + " " + // cores to use
                            PHANTOMPEAK_EXTRA

    transform(".bam") to("_phantompeak.png") {
        exec """
            ${PREPARE_R} &&
            
            ${RUN_R} ${TOOL_ENCODEqc}/phantompeak.R $input \$(basename $input.prefix) $PHANTOMPEAK_FLAGS &&
            mv *_phantompeak.* $output.dir
        ""","phantompeak"
    }

    forward input
}

