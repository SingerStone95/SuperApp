package com.singerstone.supergradle

import org.gradle.api.Project

class SuperGradleExtension{
    Project mProject
    String auchor = 'unknown'
    public SuperGradleExtension(Project project) {
        this.mProject = project
    }
}