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
    def THREADS=DUPRADAR_THREADS.replaceFirst("threads=", "")

    // run the chunk
    transform(".bam") to("_dupRadar.png") {
        exec """
            ${PREPARE_R} &&
            ${PREPARE_SUBREAD} &&
			if [ -n "\$SLURM_JOBID" ]; then
				export TMPDIR=/jobdir/\${SLURM_JOBID};
			fi &&

			base=`basename $input` &&
            if [[ "$DUPRADAR_PAIRED" == "paired=yes" ]];
	    then
	    	     echo "We are resorting and doing the repair\n" &&
		     ${RUN_REPAIR} -i $input -T $THREADS -o \${TMPDIR}/\${base} &&
		     ${RUN_R} ${TOOL_DUPRADAR}/dupRadar.R bam=\${TMPDIR}/\${base} $DUPRADAR_FLAGS;	
	    else		
			${RUN_R} ${TOOL_DUPRADAR}/dupRadar.R bam=$input $DUPRADAR_FLAGS;
	    fi
		""","dupRadar"
	}
    forward input
}

