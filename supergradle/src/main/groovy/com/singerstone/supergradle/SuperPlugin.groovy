package com.singerstone.supergradle

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class SuperPlugin implements Plugin<Project> {


    Project mProject

    SuperPlugin() {
        println("SuperPlugin")
    }

    @SuppressWarnings("NullableProblems")
    @Override
    void apply(Project project) {
        println(">>>>>>>>>>>>>>>> start super gradle <<<<<<<<<<<<<<<<<<")
        mProject = project
        printAppInfo()
        addExtension()
        createTask()
        printManifestInfo()
        ASM()
        println(">>>>>>>>>>>>>>>> end super gradle <<<<<<<<<<<<<<<<<<")


    }

    void ASM() {
        AppExtension appExtension = (AppExtension) mProject.getProperties().get("android")
        println(mProject.getProperties())
        //appExtension.registerTransform(new SpuerTrasform(), Collections.EMPTY_LIST)
    }

    void printAppInfo() {
        println("mProject.rootDir=" + mProject.rootDir)
        println("mProject.projectDir=" + mProject.projectDir)

        def sp = mProject.gradle.startParameter
        def p = sp.projectDir
        for (task in sp.taskNames) {
            println("task:" + task)
        }
        if (p == null) {
            println("sp.projectDir is null!")
        } else {
            println("p.getAbsolutePath()=" + p.getAbsolutePath())
        }

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