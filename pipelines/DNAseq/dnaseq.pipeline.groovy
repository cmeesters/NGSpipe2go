MODULE_FOLDER="./NGSpipe2go/modules/"  // adjust to your projects needs
CONFIG_FOLDER="./NGSpipe2go/config/"

load MODULE_FOLDER + "DNAseq/essential.vars.groovy"
load CONFIG_FOLDER + "tool.setup.groovy"

load MODULE_FOLDER + "NGS/fastqc.vars.groovy"
load MODULE_FOLDER + "NGS/fastqc.module.groovy"

load MODULE_FOLDER + "DNAseq/bwa.vars.groovy"
load MODULE_FOLDER + "DNAseq/bwa.module.groovy"

load MODULE_FOLDER + "NGS/rmdups.vars.groovy"
load MODULE_FOLDER + "NGS/rmdups.module.groovy"

load MODULE_FOLDER + "NGS/bamindexer.vars.groovy"
load MODULE_FOLDER + "NGS/bamindexer.module.groovy"

load MODULE_FOLDER + "DNAseq/realignment.vars.groovy"
load MODULE_FOLDER + "DNAseq/realignment.module.groovy"

load MODULE_FOLDER + "DNAseq/recalibration.vars.groovy"
load MODULE_FOLDER + "DNAseq/recalibration.module.groovy"

load MODULE_FOLDER + "DNAseq/variantcallHC.vars.groovy"
load MODULE_FOLDER + "DNAseq/variantcallHC.module.groovy"

load MODULE_FOLDER + "DNAseq/variantcallUG.vars.groovy"
load MODULE_FOLDER + "DNAseq/variantcallUG.module.groovy"

load MODULE_FOLDER + "DNAseq/variantfuseHC.vars.groovy"
load MODULE_FOLDER + "DNAseq/variantfuseHC.module.groovy"

load MODULE_FOLDER + "DNAseq/varianteval.vars.groovy"
load MODULE_FOLDER + "DNAseq/varianteval.module.groovy"

load MODULE_FOLDER + "miscellaneous/collectbpipes.module.2.groovy"

load MODULE_FOLDER + "DNAseq/shinyreports.vars.groovy"
load MODULE_FOLDER + "DNAseq/shinyreports.module.groovy"

run {
    //"%.fastq.gz" * [ FastQC ] + "%read_*.fastq.gz" * [ BWA_pe ]
    "%.fastq.gz" * [ FastQC ] + "%_R*.fastq.gz" * [ BWA_pe ] + "%.bam" * [ RmDups + BAMindexer + IndelRealignment + BaseRecalibration + [ VariantCallHC, VariantCallUG ] ] + "%.vcf.gz" * [ VariantEval ] + collectBpipeLogs + shinyReports
    // "%.fastq.gz" * [ FastQC ] + "%read_*.fastq.gz" * [ BWA_pe ] + "%.bam" * [ MarkDups + IndelRealignment + BaseRecalibrate + HaplotypeCall ]
    }
