//rule for task pbc from catalog ChIPseq, version 1
//desc: PCR Bottleneck Coefficient
pbc = {
    doc title: "PBC",
        desc:  "PCR Bottleneck Coefficient",
        constraints: "",
        bpipe_version: "tested with bpipe 0.9.8.7",
        author: "Sergi Sayols"

    output.dir = QC + "/pbc"

    transform(".bam") to("_PBC.csv") {
        exec """
            ${PREPARE_R} &&

            ${RUN_R} ${TOOL_ENCODEqc}/PBC.R $input && mv ${input.prefix}_PBC.csv $output.dir
        ""","pbc"
    }
    
    forward input

}
