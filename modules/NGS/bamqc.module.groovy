BamQC = {
	doc title: "BamQC",
		desc:  "Quality control of bam file",
		constraints: "",
		bpipe_version: "tested with bpipe 0.9.9.3",
		author: "Giuseppe Petrosino"

	output.dir   = BAMQC_OUTDIR
	def BAMQC_FLAGS = "--extract --quiet"
	
	transform(".bam") to ("_bamqc.zip") {
		exec """
			${PREPARE_BAMQC} &&
			
			${RUN_BAMQC} $BamQC_FLAGS -o $output.dir $input
		""","BamQC"
	}

	forward input
}
