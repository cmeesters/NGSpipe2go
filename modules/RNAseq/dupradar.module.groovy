dupRadar = {
	doc title: "dupRadar",
		desc:  "analysis of duplication rate on RNAseq analysis",
		constraints: "",
		bpipe_version: "tested with bpipe 0.9.8.7",
		author: "Sergi Sayols"

	output.dir = DUPRADAR_OUTDIR.replaceFirst("outdir=", "")
	def DUPRADAR_FLAGS = DUPRADAR_GTF      + " " +
	                     DUPRADAR_STRANDED + " " + 
			    		 DUPRADAR_PAIRED   + " " +
						 DUPRADAR_OUTDIR   + " " +
				    	 DUPRADAR_THREADS  + " " +
                         DUPRADAR_EXTRA

	// run the chunk
	transform(".bam") to("_dupRadar.png") {
		exec """
			module load R &&
			
			echo 'VERSION INFO'  1>&2 ;
			echo \$(${TOOL_R}/bin/Rscript --version 2>&1 | cut -d' ' -f5) 1>&2 ;
			echo '/VERSION INFO'  1>&2 ;
			
			${TOOL_R}/bin/Rscript ${TOOL_DUPRADAR}/dupRadar.R bam=$input $DUPRADAR_FLAGS
		""","dupRadar"
	}

	forward input
}

