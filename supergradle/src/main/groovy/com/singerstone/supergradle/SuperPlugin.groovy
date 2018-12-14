package com.singerstone.supergradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.singerstone.supergradle.SuperConfig

class SuperPlugin implements Plugin<Project> {


    Project mProject

    SuperPlugin() {
        println("SuperPlugin")
    }

    @Override
    void apply(Project project) {
        println(">>>>>>>>>>>>>>>> start super gradle <<<<<<<<<<<<<<<<<<")
        mProject = project
        println(project.rootDir)
        println(project.projectDir)
        def sp = project.gradle.startParameter
        def p = sp.projectDir
        for (task in sp.taskNames) {
            println(task)
        }
        if (p == null) {
            println("sp.projectDir is null!")
        } else {
            println(p.getAbsolutePath())
        }
        addExtension()
        createTask()
       // printManifestInfo()
        println(">>>>>>>>>>>>>>>> end super gradle <<<<<<<<<<<<<<<<<<")
    }

    void printManifestInfo() {
        String xmlFile = mProject.rootDir.toString() + "/app/src/main/AndroidManifest.xml"
        println(xmlFile)
        File file = new File(xmlFile)
        def manifest = new XmlParser().parseText(file.text)
        println(manifest.attributes().get("package"))
        def activitys = manifest.application
        //println(activitys)
        activitys.activity.each {
            println(it)
        }
    }

    void addExtension() {
        mProject.extensions.add("superconfig", SuperConfig)
    }

    void createTask() {
        mProject.task("printConfig") {
            group 'super'
            doLast {
                SuperConfig config = mProject.superconfig
                println(config.name)
            }
        }
    }
}