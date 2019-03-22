//rule for task GATK realignment from DNAseq pipeline
//desc: Indel realignment provided by GATK
IndelRealignment = {
    
    doc title: "GATK IndelRealignment",
	desc:  "Realign BAM files at Indel positions, using GATK",
	constraints: "Requires BWA ( paramteter -M ) produced BAM file, with correct chromosome order and ReadGroup attached.",
	bpipe_version: "tested with bpipe 0.9.8.7",
	author: "Oliver Drechsel"

	output.dir = MAPPED
    def GATK_FLAGS = "-known gold_indels.vcf "
    
    // check if a region limit was provided
    if (GATK_CALL_REGION!=null && GATK_CALL_REGION.length()>0) {
        GATK_FLAGS = GATK_FLAGS + " -L " + GATK_CALL_REGION
    } else {
        GATK_FLAGS = ""
    }
        
    transform (".bam") to (".realignment.targets.bed", ".realigned.bam") {
        exec """
       
            ${PREPARE_JAVA} &&
            if [ -n "\$SLURM_JOBID" ]; then
				export TMPDIR=/jobdir/\${SLURM_JOBID};
			fi                           &&

            ${RUN_JAVA} -Djava.io.tmpdir=$TMPDIR -jar ${TOOL_GATK}/GenomeAnalysisTK.jar -T RealignerTargetCreator -nt $GATK_THREADS -R $GATK_BWA_REF -I $input -o $output1 &&
            ${RUN_JAVA} -Djava.io.tmpdir=$TMPDIR -jar ${TOOL_GATK}/GenomeAnalysisTK.jar -T IndelRealigner -R $GATK_BWA_REF -I $input -targetIntervals $output1 -o $output2;
       ""","IndelRealignment"
    }
    
}
