package com.github.skosmalla.conditus.step

import com.github.skosmalla.conditus.CommandLineExecutor
import com.github.skosmalla.conditus.OsSpecifics
import com.github.skosmalla.conditus.ScmUrlUtil
import com.github.skosmalla.conditus.exception.StepExecutionException

/**
 * @author skosmalla
 * @since 11.06.14
 */
class PrepareSvnForTagStep extends PrepareScmStep {

    PrepareSvnForTagStep(String scmUrl) {
        super(scmUrl)
    }

    @Override
    void execute() {
        String tagUrl = createTagUrl()

        if(!isTagUrlExisting(tagUrl)){
            createTagFolder(tagUrl)
        }
    }

    private void createTagFolder(String tagUrl) {
        def command = OsSpecifics.getOsName().commandLinePrefix + "svn mkdir " + tagUrl + " -m \"create tags folder\""
        CommandLineExecutor.getInstance().executeCommand(command)

        println("Create path " + tagUrl)
    }

    private boolean isTagUrlExisting(String tagUrl) {
        def command = OsSpecifics.getOsName().commandLinePrefix + "svn ls " + tagUrl

        boolean exists = true
        try{
            CommandLineExecutor.getInstance().executeCommand(command)
            println("Path " + tagUrl + " exists.")
        } catch (StepExecutionException exception) {
            println("Path " + tagUrl + " doesn't exist.")
            exists = false
        }

        exists
    }

    private String createTagUrl() {
        def repositoryUrl = ScmUrlUtil.getRepositoryUrl(scmUrl)
        def tagUrl = ScmUrlUtil.replace(repositoryUrl, 'tags')
        tagUrl
    }
}
