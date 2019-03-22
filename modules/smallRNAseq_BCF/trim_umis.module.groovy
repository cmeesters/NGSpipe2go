TrimUMIs = {
	doc title: "Trim UMIs",
	desc:  """Trims random barcodes that help in the identification of PCR duplicates and are in adapter-removed reads: NNNN-insert-NNNN.""",
      	constraints: "Requires seqtk.",
	author: "Antonio Domingues, Anke Busch"

	output.dir = TRIM_OUTDIR
	def SEQTK_FLAGS = 	" -l " + LEFT_TRIM + 
				" -b " + RIGHT_TRIM

	transform(".fastq.gz") to (".trimmed.fastq.gz") {

		exec """

            ${PREPARE_SEQTK} &&

		    ${RUN_SEQTK} trimfq -b ${LEFT_TRIM} -e ${RIGHT_TRIM} $input | gzip > $output

		""","TrimUMIs"
	}
}
