FILBOWTIE2UNIQUE_MAPPED=MAPPED
FILBOWTIE2UNIQUE_SAMTOOLS_THREADS=ESSENTIAL_THREADS
FILBOWTIE2UNIQUE_GENOME=ESSENTIAL_BOWTIE_GENOME
FILBOWTIE2UNIQUE_SAMTOOLS_MAPQ=" -q 3 " // MAPQ >=3 should exclude "true multireads", multi mapped reads within the window of insert size
FILBOWTIE2UNIQUE_SAMTOOLS_THREADS=" -@ " + Integer.toString(ESSENTIAL_THREADS) // threads to use
