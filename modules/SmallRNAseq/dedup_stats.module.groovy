DedupStats = {
    doc title: "Statistics of unique reads",
        desc:  "Counts the number of reads in the original reads file, and after PCR duplicate removal, and plots results",
        author: "Antonio Domingues"

    def OUT_PDF = REMOVE_DUP_OUTDIR + "/figure/PCRDuplicates.pdf"
    def OUT_PNG = REMOVE_DUP_OUTDIR + "/figure/PCRDuplicates.png"
    produce(OUT_PDF,
           OUT_PNG) {

      exec """
         ${PREPARE_R} &&

         cd ${REMOVE_DUP_OUTDIR} &&
         ${RUN_R} ${DEDUP_PLOT_TOOL}

        ""","DedupStats"
    }
}
