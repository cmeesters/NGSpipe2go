MODULE_FOLDER="NGSpipe2go/modules/" // adjust to your projects needs
CONFIG_FOLDER="./NGSpipe2go/config/"

load MODULE_FOLDER + "SmallRNAseq/essential.vars.groovy"
load CONFIG_FOLDER + "tool.setup.groovy"

load MODULE_FOLDER + "SmallRNAseq/fastqc.vars.groovy"
load MODULE_FOLDER + "SmallRNAseq/fastqc.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/cutadapt.vars.groovy"
load MODULE_FOLDER + "SmallRNAseq/cutadapt.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/fastq_quality_filter.vars.groovy"
load MODULE_FOLDER + "SmallRNAseq/fastq_quality_filter.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/dedup.vars.groovy"
load MODULE_FOLDER + "SmallRNAseq/dedup.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/dedup_stats.vars.groovy"
load MODULE_FOLDER + "SmallRNAseq/dedup_stats.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/trim_umis.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/trim_umis.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/bowtie1.vars.groovy"
load MODULE_FOLDER + "SmallRNAseq/bowtie1.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/select_uniq_mappers.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/select_uniq_mappers.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/bamindexer.module.groovy"

load MODULE_FOLDER + "SmallRNAseq/maping_stats.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/maping_stats.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/read_count.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/read_count.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/read_count_summary.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/read_count_summary.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/split_read_strands.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/split_read_strands.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/bam2bw.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/bam2bw.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/nucleotide_signature.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/nucleotide_signature.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/ping_pong_signal.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/ping_pong_signal.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/ping_pong_pro.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/ping_pong_pro.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/collect_plots.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/collect_plots.vars.groovy"

load MODULE_FOLDER + "SmallRNAseq/repenrich.module.groovy"
load MODULE_FOLDER + "SmallRNAseq/repenrich.vars.groovy"


//MAIN PIPELINE TASK
run {
	"%.fastq.gz" * 
	[ FastQC , Cutadapt + FastQQualityFilter + FilterDuplicates + TrimUMIs ] +
	"%.deduped_barcoded.trimmed.fastq.gz" * 
	[ FastQC, RepEnrich, Bowtie_se + [ BAMindexer, SelectUniqMappers + [ NucleotideSignature, PingPongSignal, PingPongPro] ] ] +
	"%.bam" *
	[ CountReads, Bam2bw, SplitReadStrands +
		"%sense.bam" * [Bam2bw] ] +
	[ DedupStats, MappingStatsPlot, CountReadsSummary ] +
	[ CollectPlots ]
}
