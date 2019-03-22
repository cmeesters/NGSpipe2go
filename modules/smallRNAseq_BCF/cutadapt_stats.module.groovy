CutadaptStats = {
		doc title: "Adapter trimming statistics",
		desc:  "Creates a plot summarizing the amount of reads left after trimming off the adapter",
		author: "Anke Busch"

		output.dir = REMOVE_ADAPTER_PLOTDIR

		produce(REMOVE_ADAPTER_PLOTDIR + "/trimmedReads.pdf", REMOVE_ADAPTER_PLOTDIR + "/trimmedReads.png") {

			exec """

                ${PREPARE_R} &&

        	 		${RUN_R} ${CUTADAPT_PLOT_TOOL} ${REMOVE_ADAPTER_DATADIR} ${REMOVE_ADAPTER_PLOTDIR} ${ESSENTIAL_SAMPLE_PREFIX}

			""","CutadaptStats"
		}
}
