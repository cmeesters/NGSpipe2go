ESSENTIAL_PROJECT=""   // full project directory path
ESSENTIAL_BOWTIE_REF="genome"  // full path to reference index files (base name) needed for Bowtie (SR data) or Bowtie2 (PE data)
ESSENTIAL_BOWTIE_GENOME="genome.fa"  // full path to the reference genome FASTA file
ESSENTIAL_CHROMSIZES="mm10.chrom.sizes"  // chromosome sizes file of the reference genome
ESSENTIAL_SAMPLE_PREFIX=""  // sample prefix to be trimmed in the results and reports
ESSENTIAL_BLACKLIST="mm10.blacklist.bed"
ESSENTIAL_BSGENOME="BSgenome.Mmusculus.UCSC.mm10"  // Bioconductor genome reference used by some modules
ESSENTIAL_TXDB="TxDb.Mmusculus.UCSC.mm10.knownGene" // needed for peak annotation
ESSENTIAL_ANNODB="org.Mm.eg.db"                    // needed for peak annotation
ESSENTIAL_READLEN=42  // read length
ESSENTIAL_FRAGLEN=200   // mean length of library inserts and also minimum peak size called by MACS2
ESSENTIAL_DUP="auto"  // how MACS2 deals with duplicated reads or fragments ("auto", "no" or 1)
ESSENTIAL_MACS2_GSIZE="mm"  // mappable genome size for MACS2 (use approx. number or "hs" for human, "mm" for mouse, "ce" for worm, "dm" for fly)
ESSENTIAL_THREADS=4   // number of threads for parallel tasks
ESSENTIAL_DB="mm10"  // UCSC assembly version for GREAT analysis (only for UCSC hg19, hg18, mm9 and danRer7)
ESSENTIAL_FRAGMENT_USAGE="yes" // "no" for SR data; "yes" for PE data to make bigWig tracks with reconstituted fragments
ESSENTIAL_PAIRED="yes"   // to perform MACS2 peak calling in SR mode ("no") or PE mode ("yes") 
ESSENTIAL_STRANDED="no"  // library prep protocol strandness: no|yes|reverse
ESSENTIAL_BAMCOVERAGE="--binSize 10 --normalizeUsing CPM"  // deepTools options for making normalised bigWig tracks

//global vars that will be reused in some global vars
PROJECT=ESSENTIAL_PROJECT
LOGS=PROJECT + "/logs"
MAPPED=PROJECT + "/mapped"
QC=PROJECT + "/qc"
REPORTS=PROJECT + "/reports"
RESULTS=PROJECT + "/results"
TMP=PROJECT + "/tmp"
TRACKS=PROJECT + "/tracks"

// optional pipeline stages to include
RUN_USING_UNFILTERED_BAM=false  // has effect only for PE data. Defaults to false (remove duplicates from BAM file)
RUN_PEAK_ANNOTATION=true
RUN_DIFFBIND=false
RUN_TRACKHUB=false
