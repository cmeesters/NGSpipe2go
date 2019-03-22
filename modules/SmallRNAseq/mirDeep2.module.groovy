MirDeep2 = {
	doc title: "mirDeep2",
		desc:  """Quantification of miRNAs performed in 2 steps: (1) Processes reads and mappping to the reference genome; (2) quantification of miRNA expression. This is step 2""",
      constraints: "Requires mirDeep2.",
		author: "Antonio Domingues"

   def EXP = input1.split("/")[-1].replaceAll(".arf", "")
	output.dir = MIR_OUTDIR + "/" + EXP

	transform(".arf", ".fa") to (".tmp") {


      exec """
         ${PREPARE_MIRDEEP2} &&

         cd $output.dir &&

         ${RUN_MIRDEEP2} $input2 $GENOME_SEQ $input1 $MATURE_MIRNA none $HAIRPIN_MIRNA -t zebrafish -c -d -v -r ${EXP} -z "."${EXP} 2> ${EXP}.report.log &&

         touch ${EXP}.tmp

		""","MirDeep2"
	}
}
