bamCoverage = {
	doc title: "bamCoverage",
		desc:  "bamCoverage wrapper",
		constraints: "normalised bigwig track for RNA/ChipSeq PE data",
		bpipe_version: "tested with bpipe 0.9.8.7",
		author: "Nastasja Kreim"

		output.dir = BAMCOVERAGE_OUTDIR
		BAMCOVERAGE_FLAGS = BAMCOVERAGE_CORES + " " + BAMCOVERAGE_OTHER
		if( BAMCOVERAGE_FRAGMENTS == "yes") {
			BAMCOVERAGE_FLAGS = BAMCOVERAGE_FLAGS + " --extendReads"
		}

	transform(".bam") to(".bw") {
		exec """
			${PREPARE_DEEPTOOLS}  &&
			if [ -n "\$SLURM_JOBID" ]; then
				export TMPDIR=/jobdir/\${SLURM_JOBID};
			fi;

			${RUN_BAMCOVERAGE} $BAMCOVERAGE_FLAGS --bam $input -o ${output};
		""","bamCoverage"
	}
}

