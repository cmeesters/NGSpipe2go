PlotReadLengths = {
    doc title: "PlotReadLengths",
    desc: "Plots the read length distribution of libraries (fastq).",
    constraints: "none",
    author: "António Domingues"

    output.dir = PLOT_READ_LENGTH_OUTDIR

    produce(PLOT_READ_LENGTH_OUTDIR + "/figure/PercentageReadsLengthDistribution.pdf"){
        
        exec """
            ${PREPARE_R} &&
            ${RUN_R} $PLOT_READ_LENGTH_TOOL_PATH $PLOT_READ_LENGTH_OUTDIR

      ""","PlotReadLengths"
    }
}
