ESSENTIAL_PROJECT="/fsimb/groups/imb-bioinfocf/projects/cfb_internal/tmp/ngspipe2go_chipseq_test"
ESSENTIAL_BOWTIE_REF="/fsimb/groups/imb-bioinfocf/common-data/igenomes_reference/Mus_musculus/UCSC/mm10/Sequence/BowtieIndex/genome"
ESSENTIAL_BOWTIE_GENOME="/fsimb/groups/imb-bioinfocf/common-data/igenomes_reference/Mus_musculus/UCSC/mm10/Sequence/BowtieIndex/genome.fa"
ESSENTIAL_CHROMSIZES="/fsimb/common/genomes/mus_musculus/ucsc/mm10/full/genome/mm10.chrom.sizes"
ESSENTIAL_SAMPLE_PREFIX=""  // sample prefix to be trimmed in the results and reports
ESSENTIAL_BLACKLIST="/fsimb/common/genomes/mus_musculus/ucsc/mm10/full/annotation/mm10.blacklist.bed"
ESSENTIAL_BSGENOME="BSgenome.Mmusculus.UCSC.mm10"  // Bioconductor genome reference used by some modules
ESSENTIAL_TXDB="TxDb.Mmusculus.UCSC.mm10.knownGene" // needed for peak annotation
ESSENTIAL_ANNODB="org.Mm.eg.db"                    // needed for peak annotation
ESSENTIAL_READLEN=39  // read length
ESSENTIAL_FRAGLEN=100   // mean length of library inserts and also minimum peak size called by MACS2
ESSENTIAL_DUP="auto"  // how MACS2 deals with duplicated reads or fragments ("auto", "no" or 1)
ESSENTIAL_MACS2_GSIZE="mm"  // mappable genome size for MACS2 (use approx. number or "hs" for human, "mm" for mouse, "ce" for worm, "dm" for fly)
ESSENTIAL_THREADS=8   // number of threads for parallel tasks
ESSENTIAL_DB="mm10"  // UCSC assembly version for GREAT analysis (only for UCSC hg19, hg18, mm9 and danRer7)
ESSENTIAL_FRAGMENT_USAGE="no" // "no" for SR data; "yes" for PE data to make bigWig tracks with reconstituted fragments
ESSENTIAL_PAIRED="no"   // to perform MACS2 peak calling in SR mode ("no") or PE mode ("yes") 
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
