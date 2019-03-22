//rule for task FastQScreen from catalog smallRNAseq_BCF, version 0.1
//desc: A quality control tool for high throughput sequence data.
FastQScreen = {
	doc title: "FastQScreen",
	desc:  "Quality control of input file against various contaminants",
	constraints: "Only supports compressed FASTQ files",
	author: "Nastasja Kreim, Anke Busch"

	output.dir   = FASTQSCREEN_OUTDIR
	def FASTQSCREEN_FLAGS = "--threads " + FASTQSCREEN_THREADS + " " + FASTQSCREEN_PARAM

	transform(".fastq.gz") to ("_fastqscreen.done") {

		def SAMPLENAME = output.prefix

		exec """

            ${PREPARE_FASTQSCREEN} &&
			
			if [ ! -e "$output.prefix" ]; then
		                mkdir $output.prefix;
		        fi &&
	    	
			FASTQREFERENCES=$FASTQSCREEN_CONF;
			REFERENCES=(\${FASTQREFERENCES//,/ });

			for i in "\${!REFERENCES[@]}";
			do
				REFERENCE=(\${REFERENCES[i]//::/ });
				echo -e "DATABASE\t\${REFERENCE[0]}\t\${REFERENCE[1]}" >> $output.prefix/fastqscreen.conf;
			done;

			SAMPLENAME_BASE=\$(basename ${SAMPLENAME}) &&
			${RUN_FASTQSCREEN} $FASTQSCREEN_FLAGS --conf $output.prefix/fastqscreen.conf --outdir $output.prefix $input 2> $output.dir/\${SAMPLENAME_BASE}.log;
			touch $output;

		""","FastQScreen"
	}

	forward input
}

