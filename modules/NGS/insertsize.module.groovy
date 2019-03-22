//rule for task Insertsize from catalog NGS
//desc: get the insert size from a bam paired end bam file
InsertSize = {
	doc title: "InsertSize",
		desc:  "Call picard tools create insert size values",
		constraints: "",
		bpipe_version: "tested with bpipe 0.9.8.7",
		author: "Nastasja Kreim"

	output.dir=INSERTSIZE_OUTDIR
	def INSERTSIZE_FLAGS = "ASSUME_SORTED=TRUE"
	def JAVA_FLAGS  = "-Xmx" + INSERTSIZE_MAXMEM + "m"

	transform(".bam") to ("_insertsizemetrics.tsv") {
		exec """
		    ${PREPARE_PICARD} && 
            
            ${RUN_JAVA} $JAVA_FLAGS -jar ${TOOL_PICARD}/picard.jar CollectInsertSizeMetrics $INSERTSIZE_FLAGS INPUT=$input OUTPUT=$output HISTOGRAM_FILE=${input.prefix}_insertsize_hist.pdf
		""","InsertSize"
	}
}

