In order to configure apache nutch-1.7 and solr-4.5 make follow the next steps:
1) download both binary folders
2) configure $APACHE_SOLR_HOME and $NUTCH_RUNTIME_HOME environment variables to appropriate locations
3) copy {NUTCH_RUNTIME_HOME}/conf/schema-solr4.xml to ${APACHE_SOLR_HOME}/example/solr/collection1/conf/schema.xml (replace)
4) add
        <field name="_version_" type="string" stored="true" indexed="true" multiValued="false"/>
    under <fields>...</fields> to make sure that a) solr client will start b) nutch job will complete