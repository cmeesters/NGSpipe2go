//rule for task geneBodyCov from catalog RNAseq, version 1
//desc: Calculate the RNA-seq coverage over gene body
geneBodyCov2 = {
    doc title: "geneBodyCoverage2",
        desc:  """Calculate the RNA-seq coverage over gene body. 
            Useful to check the 5' or 3' coverage bias""",
        constraints: "",
        bpipe_version: "tested with bpipe 0.9.9.9",
        author: "Sergi Sayols"

    output.dir = GENEBODYCOV2_OUTDIR.replaceFirst("outdir=", "")
    def GENEBODYCOV2_FLAGS = GENEBODYCOV2_GTF      + " " +
                             GENEBODYCOV2_PAIRED   + " " +
                             GENEBODYCOV2_STRANDED + " " +
                             GENEBODYCOV2_OUTDIR   + " " +
                             GENEBODYCOV2_THREADS

    // run the chunk
    transform(".bam") to ("_geneBodyCov.png") {
        exec """
            ${PREPARE_R} &&

            if [[ ! -e "$output.dir" ]]; then
                mkdir -p "$output.dir";
            fi &&

            ${RUN_R} ${TOOL_GENEBODYCOV2}/geneBodyCov.R bam=$input $GENEBODYCOV2_FLAGS
        ""","geneBodyCov2"
    }
    forward input
}
