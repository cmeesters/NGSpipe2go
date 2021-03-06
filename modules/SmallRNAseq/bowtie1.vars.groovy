MULTIMAP_OUT_DIR=MAPPED
BOWTIE_THREADS=ESSENTIAL_THREADS			// threads to use
BOWTIE_REF=ESSENTIAL_BOWTIE_REF // prefix of the bowtie reference genome
BOWTIE_MM=ESSENTIAL_MISMATCHES             // number of mismatches allowed
BOWTIE_MULTIMAP=1       // discard reads mapping to more than MULTIMAP positions
BOWTIE_MULTIREPORT=1       // if a read has more than <int> reportable alignments, one is reported (as mapped) at random.
BOWTIE_BEST="--tryhard --best --strata --chunkmbs 256"	// bowtie best mode
BOWTIE_QUALS="--phred33-quals"	// phred33-quals. Use --phred64-quals for old sequencing runs
