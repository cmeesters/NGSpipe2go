GO_Enrichment = {
    doc title: "GO_Enrichment",
        desc: "Gene Ontology enrichment analysis",
        constraints: "",
        bpipe_version: "",
        author: ""

     output.dir = GO_Enrichment_OUTDIR.replaceFirst("out=", "")
    def GO_Enrichment_FLAGS = GO_Enrichment_LOG2FOLD + " " + 
                              GO_Enrichment_PADJ     + " " +
                              GO_Enrichment_ORG      + " " +
                              GO_Enrichment_UNIV     + " " +
                              GO_Enrichment_TYPE     + " " +
                              GO_Enrichment_CATEGORY + " " +
                              GO_Enrichment_OUTDIR   + " " +
                              GO_Enrichment_CORES    + " " +
                              GO_Enrichment_EXTRA

    transform(".RData") to("_GO.done") {
        exec """
        ${PREPARE_R} &&
        touch $output &&
        ${RUN_R} ${TOOL_GO}/GO_Enrichment.R rData=$input $GO_Enrichment_FLAGS &&
        if [ \$? -ne 0 ]; then rm $output; fi;
    ""","GO_Enrichment"
    }

}
