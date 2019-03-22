CountNonStrutReads = {
   doc title: "CountNonStrutReads",
   desc: "Summarizes the number of non-structural reads for each the libraries. It uses the output from featureCounts",
   constraints: "needs a table with gene names and biotypes",
   author: "António Domingues"

   output.dir = COUNT_NONSTRUCT_OUTDIR

   from("*.counts") produce(COUNT_NONSTRUCT_OUTDIR + "/normalization_factors.txt"){
      exec """

         ${PREPARE_R} &&
         
         if [ -n "\$LSB_JOBID" ]; then
            export TMPDIR=/jobdir/\${LSB_JOBID};
         fi &&

         cd $output.dir &&

         ${RUN_R} $COUNT_NONSTRUCT_TOOL_PATH $ESSENTIAL_BIOTYPES_TABLE $inputs
         
      ""","CountNonStrutReads"
   }
}
