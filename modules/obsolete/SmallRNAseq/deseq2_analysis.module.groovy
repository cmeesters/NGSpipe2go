load MODULE_FOLDER + "SmallRNAseq/deseq2_analysis.vars.groovy"

PerformDEGAnalaysis = {
   doc title: "PerformDEGAnalaysis",
   desc: "Creates DEG report",
   constraints: "needs a table with gene names and biotypes",
   author: "António Domingues"

   output.dir = HTSEQCOUNT_OUT

   produce("normlization_factors.txt"){
      exec """

         module load R/${R_VERSION} &&

         cd $output.dir &&
         ln -s $DEG_SCRIPT . &&
         ln -s $DEG_SCRIPT_HELPERS . &&

         knit_to_html.sh $DEG_SCRIPT
      ""","PerformDEGAnalaysis"
   }
}