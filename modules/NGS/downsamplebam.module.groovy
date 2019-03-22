//rule Task to downsample BAM file to roughly a number of given reads (paired and single end compatible) 
//desc:
DownsampleBAM = {
	doc title: "DownsampleBAM",
		desc:  "Call samtools tools to downsample a given bam file to roughly a given number of mapped reads",
		constraints: "Samtools tools version >= 1.3",
		bpipe_version: "tested with bpipe 0.9.9.5",
		author: "Nastasja Kreim"

	output.dir = DOWNSAMPLED

	transform(".bam") to (".down.bam") {
		exec """
      ${PREPARE_SAMTOOLS} &&
			if [ -n "\$SLURM_JOBID" ]; then
				export TMPDIR=/jobdir/\${SLURM_JOBID};
			fi &&
      BASE=\$(basename $input) &&
      ${RUN_SAMTOOLS} view -F 0x04 -bh ${input} -o \${TMPDIR}/\${BASE}_mapped.bam &&
      TOTAL_MAPPED=\$(${RUN_SAMTOOLS} flagstat \${TMPDIR}/\${BASE}_mapped.bam | grep mapped | head -n 1 | awk '{print \$1 }') &&
      echo mapped_info \$TOTAL_MAPPED &&
      if [[ $DOWNSAMPLE_AMOUNT > \$TOTAL_MAPPED ]]; then
        echo "Downsample amount higher than amount of mapped reads. Keeping all reads!" &&
        cp \${TMPDIR}/\${BASE}_mapped.bam $output;
      else
          PROBABILITY=\$(echo "$DOWNSAMPLE_SEED + $DOWNSAMPLE_AMOUNT/\$TOTAL_MAPPED" | bc -l);
      echo Probability \$PROBABILITY &&
      ${RUN_SAMTOOLS} view -bs \$PROBABILITY -o $output \${TMPDIR}/\${BASE}_mapped.bam;
      fi
      ""","DownsampleBAM"
	}
}

