//vars for task Cutadapt from catalog SmallRNAseq, version 0.1

CUTADAPT_OUTDIR=PROCESSED
ADAPTER_SEQUENCE="-a " + ESSENTIAL_ADAPTER_SEQUENCE // adapter to be trimmed off
MINIMUM_OVERLAP=ESSENTIAL_MINADAPTEROVERLAP
MINIMUM_LENGTH_KEEP=ESSENTIAL_MINREADLENGTH
MAXIMUM_LENGTH_KEEP=ESSENTIAL_MAXREADLENGTH
