package com.singerstone.supergradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class SuperPlugin implements Plugin<Project> {


    Project mProject
    SuperPlugin(){
        println("SuperPlugin")
    }

    @Override
    void apply(Project project) {
        println(">>>>>>>>>>>>>>>> start super gradle >>>>>>>>>>>>>>>>")
        mProject=project
        def sp = project.gradle.startParameter
        def p = sp.projectDir
        for (task in sp.taskNames){
            println(task)
        }
        println(p.getAbsolutePath())
        createExtension()
        createTask()
    }

    void createExtension(){
        mProject.extensions.create('supergradle', SuperGradleExtension.class, mProject)
        println(((SuperGradleExtension)(mProject.supergradle)).auchor)

    }
    void createTask(){

    }
}