MODULE_FOLDER="./NGSpipe2go/modules/"
CONFIG_FOLDER="./NGSpipe2go/config/"

load MODULE_FOLDER + "ChIPseq/essential.vars.groovy"
load CONFIG_FOLDER + "tool.setup.groovy"

load MODULE_FOLDER + "NGS/fastqc.vars.groovy"
load MODULE_FOLDER + "NGS/fastqc.module.groovy"

load MODULE_FOLDER + "ChIPseq/bowtie1.vars.groovy"
load MODULE_FOLDER + "ChIPseq/bowtie1.module.groovy"

load MODULE_FOLDER + "NGS/bamindexer.vars.groovy"
load MODULE_FOLDER + "NGS/bamindexer.module.groovy"

load MODULE_FOLDER + "NGS/bamqc.vars.groovy"
load MODULE_FOLDER + "NGS/bamqc.module.groovy"

load MODULE_FOLDER + "NGS/rmdups.vars.groovy"
load MODULE_FOLDER + "NGS/rmdups.module.groovy"

load MODULE_FOLDER + "NGS/extend.vars.groovy"
load MODULE_FOLDER + "NGS/extend.module.groovy"

load MODULE_FOLDER + "NGS/bam2bw.vars.groovy"
load MODULE_FOLDER + "NGS/bam2bw.module.groovy"

load MODULE_FOLDER + "NGS/bamcoverage.vars.groovy"
load MODULE_FOLDER + "NGS/bamcoverage.module.groovy"

load MODULE_FOLDER + "NGS/trackhub_config.vars.groovy"
load MODULE_FOLDER + "NGS/trackhub_config.module.groovy"

load MODULE_FOLDER + "NGS/trackhub.vars.groovy"
load MODULE_FOLDER + "NGS/trackhub.module.groovy"

load MODULE_FOLDER + "ChIPseq/ipstrength.vars.groovy"
load MODULE_FOLDER + "ChIPseq/ipstrength.module.groovy"

load MODULE_FOLDER + "ChIPseq/phantompeak.vars.groovy"
load MODULE_FOLDER + "ChIPseq/phantompeak.module.groovy"

load MODULE_FOLDER + "ChIPseq/pbc.module.groovy"

load MODULE_FOLDER + "ChIPseq/macs2.vars.groovy"
load MODULE_FOLDER + "ChIPseq/macs2.module.groovy"

load MODULE_FOLDER + "ChIPseq/blacklist_filter.vars.groovy"
load MODULE_FOLDER + "ChIPseq/blacklist_filter.module.groovy"

load MODULE_FOLDER + "ChIPseq/peak_annotation.vars.groovy"
load MODULE_FOLDER + "ChIPseq/peak_annotation.module.groovy"               

load MODULE_FOLDER + "ChIPseq/GREAT.vars.groovy"
load MODULE_FOLDER + "ChIPseq/GREAT.module.groovy"             

load MODULE_FOLDER + "miscellaneous/collectbpipes.module.2.groovy"

load MODULE_FOLDER + "ChIPseq/shinyreports.vars.groovy"
load MODULE_FOLDER + "ChIPseq/shinyreports.module.groovy"

//MAIN PIPELINE TASK
run {
	"%.fastq.gz" * 
	[ FastQC , bowtie_se + BAMindexer + BamQC + [ extend + bamCoverage , phantompeak , pbc , ipstrength , macs2 ] ] + 
	peak_annotation +
//	trackhub_config + trackhub + 
        collectBpipeLogs + shinyReports
}
