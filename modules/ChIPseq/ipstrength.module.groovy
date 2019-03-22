//rule for task ipstrength from catalog ChIPseq, version 1
//desc: IPstrength
ipstrength = {
    doc title: "IPstrength plot",
        desc:  "IPstrength",
        constraints: "install the right reference BSgenome",
        bpipe_version: "tested with bpipe 0.9.8.7",
        author: "Sergi Sayols"

    output.dir = QC + "/ipstrength"

    transform(".bam") to("_ipstrength.done") {
        exec """
            ${PREPARE_R} &&

            touch $output;
            if [ ! -e $IPSTRENGTH_TARGETS ]; then
                echo "Targets file $IPSTRENGTH_TARGETS doesn't exist" >> $output &&
                exit 0;
            fi;
            
            BAM=\$(basename $input) &&
            grep \$BAM $IPSTRENGTH_TARGETS | while read -r TARGET; do
                IP=\$(       echo $TARGET | tr '\t' ' ' | cut -f1 -d" ") &&
                IPname=\$(   echo $TARGET | tr '\t' ' ' | cut -f2 -d" ") &&
                INPUT=\$(    echo $TARGET | tr '\t' ' ' | cut -f3 -d" ") &&
                INPUTname=\$(echo $TARGET | tr '\t' ' ' | cut -f4 -d" ");

                if [ "\$BAM" != "\$INPUT" ]; then
                    echo "\${IPname} vs \${INPUTname}" >> $output ;
                    ${RUN_R} ${TOOL_ENCODEqc}/IPstrength.R $IPSTRENGTH_MAPPED/\$IP \$IPname $IPSTRENGTH_MAPPED/\$INPUT \$INPUTname \${IPname}.vs.\${INPUTname}_ipstrength $IPSTRENGTH_BSGENOME;
                    if [ \$? -ne 0 ]; then rm $output; fi;
                    mv \${IPname}.vs.\${INPUTname}_ipstrength* $output.dir;
                fi;
            done
        ""","ipstrength"
    }

    forward input
}

