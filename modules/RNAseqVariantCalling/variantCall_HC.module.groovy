// rule for task GATK BaseRecalibrator from the RNAseq variant calling pipeline
VariantCallHC = {
   doc title: "GATK HaplotypeCaller",
       desc: "Call variants, using GATK HaplotypeCaller.",
       constraints: "GATK version >= 3.5",
       author: "Antonio Domingues"

   output.dir = OUTPUT_HC

   def JAVA_FLAGS = "-Xmx" + HC_MAXMEM
   def GATK_FLAGS  = " -R " + GATK_REF +
                     " -nct " + GATK_THREADS +
                     " -stand_call_conf " + MIN_SCORE_CALL +
                     " -stand_emit_conf " + MIN_SCORE_EMIT +
                     " -dontUseSoftClippedBases"

   transform (".rg.duprm.split.recalibrated.bam") to (".UG.vcf.gz") {

      exec """
            echo 'VERSION INFO'  1>&2 &&
            echo \$(${RUN_JAVA} -jar ${TOOL_GATK}/GenomeAnalysisTK.jar --version) 1>&2 &&
            echo '/VERSION INFO' 1>&2 &&

            ${RUN_JAVA} $JAVA_FLAGS -jar ${TOOL_GATK}/GenomeAnalysisTK.jar -T HaplotypeCaller -I $input -o $output --dbsnp $VCF_REF  $GATK_FLAGS

      ""","VariantCallHC"
   }
}
