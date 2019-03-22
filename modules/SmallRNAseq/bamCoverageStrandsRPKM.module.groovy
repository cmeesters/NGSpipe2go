//rule for task Bam2bw from catalog NGS, version 1
//desc: Create scaled bigwig tracks from a bam file
BamCoverageStrandsRPKM = {
	doc title: "BamCoverage",
		desc:  "Convert BAM file to bigWig for each strand. Normalization factor is the total number of reads.",
		constraints: "Requires deepTools >= 2.2",
		author: "Antonio Domingues"

	output.dir=BAMCOVSTRANDS_OUTDIR

    //this might be confusing regarding the reverse and forward
    //setting but according to the deeptools manual it has to be like
    //that. 
        if(ESSENTIAL_STRANDED == "yes") {
        FORWARD="reverse"
        REVERSE="forward"
    } else if(ESSENTIAL_STRANDED == "reverse") {
        FORWARD="forward"
        REVERSE="reverse"
    }

	transform(".bam") to (".RPKM.fwd.bw", ".RPKM.rev.bw") {
     	exec """
            export TOOL_DEPENDENCIES=$TOOL_DEPENDENCIES  &&
            ${PREPARE_DEEPTOOLS} &&
            ${PREPARE_SAMTOOLS} &&
            
            if [ -n "\$LSB_JOBID" ]; then
                export TMPDIR=/jobdir/\${LSB_JOBID};
            fi;

            TOTAL_MAPPED=\$( ${RUN_SAMTOOLS} flagstat $input | head -n1 | cut -f1 -d" ") &&
            SCALE=\$(echo "1000000/\$TOTAL_MAPPED" | bc -l) &&

            ${RUN_BAMCOVERAGE} --bam $input -o $output1 \
                $BAMCOVERAGE_OTHER \
                --numberOfProcessors $ESSENTIAL_CORES \
                --filterRNAstrand forward &&

            ${RUN_BAMCOVERAGE} --bam $input -o $output2 \
                $BAMCOVERAGE_OTHER \
                --numberOfProcessors $ESSENTIAL_CORES \
                --filterRNAstrand reverse 
                ""","BamCoverageStrandsRPKM"
	}
}

