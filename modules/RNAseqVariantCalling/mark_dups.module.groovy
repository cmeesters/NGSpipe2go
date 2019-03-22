// rule to add mark duplicate reads in bam
MarkDups = {
   doc title: "MarkDups",
   desc: "Call picard tools to mark with/without removing duplicated reads from a bam file",
   constraints: "Picard tools version >= 1.141"
   author: "Sergi Sayols, modified by Antonio Domingues"

   output.dir = OUTDIR_STAR2ND

   def JAVA_FLAGS = "-Xmx" + MARKDUPS_MAXMEM
   def MARKDUPS_FLAGS  = " REMOVE_DUPLICATES=" + MARKDUPS_REMOVE +
                         " CREATE_INDEX=" + MARKDUPS_INDEX +
                         " VALIDATION_STRINGENCY=" + MARKDUPS_VALIDATION +
                         " ASSUME_SORTED=TRUE"


   transform(".rg.bam") to (".rg.duprm.bam"){

      exec """
            echo 'VERSION INFO'  1>&2 &&
            echo \$(${RUN_JAVA} -jar ${TOOL_PICARD}/picard.jar MarkDuplicates --version) 1>&2 &&
            echo '/VERSION INFO' 1>&2 &&

            ${RUN_JAVA} $JAVA_FLAGS -jar ${TOOL_PICARD}/picard.jar MarkDuplicates $MARKDUPS_FLAGS I=$input O=$output M=${input.prefix}_dupmetrics.tsv

      ""","MarkDups"
   }
}
