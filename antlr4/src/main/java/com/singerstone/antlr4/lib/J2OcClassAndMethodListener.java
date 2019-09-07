package com.singerstone.antlr4.lib;

import org.antlr.v4.runtime.ParserRuleContext;


/**
 * 只用来分析 class ,interface , method variables
 * 具体的逻辑分析放到第二阶段,这样可以使用回溯推断匿名内部类
 */
public class J2OcClassAndMethodListener extends Java8BaseListener {

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void enterPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {
        super.enterPackageDeclaration(ctx);
    }

    @Override
    public void enterImportDeclaration(Java8Parser.ImportDeclarationContext ctx) {
        super.enterImportDeclaration(ctx);

    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        System.out.println("enterIfThenElseStatement:" + ctx.getText());
        super.enterIfThenElseStatement(ctx);

    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
        System.out.println("enterIfThenElseStatementNoShortIf:" + ctx.getText());
        super.enterIfThenElseStatementNoShortIf(ctx);
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        System.out.println("enterIfThenStatement:" + ctx.getText());
        super.enterIfThenStatement(ctx);
    }

    @Override
    public void enterSingleTypeImportDeclaration(Java8Parser.SingleTypeImportDeclarationContext ctx) {
        super.enterSingleTypeImportDeclaration(ctx);
    }


    @Override
    public void enterAssignment(Java8Parser.AssignmentContext ctx) {
        System.out.println("enterAssignment:" + ctx.getText());
        super.enterAssignment(ctx);
    }

    @Override
    public void enterAssignmentExpression(Java8Parser.AssignmentExpressionContext ctx) {
        System.out.println("enterAssignmentExpression:" + ctx.getText());
        super.enterAssignmentExpression(ctx);
    }

    @Override
    public void enterAssignmentOperator(Java8Parser.AssignmentOperatorContext ctx) {
        System.out.println("enterAssignmentOperator:" + ctx.getText());
        super.enterAssignmentOperator(ctx);
    }

    @Override
    public void exitPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {
        super.exitPackageDeclaration(ctx);
    }

    @Override
    public void enterClassBodyDeclaration(Java8Parser.ClassBodyDeclarationContext ctx) {
        super.enterClassBodyDeclaration(ctx);
    }

    @Override
    public void exitClassBodyDeclaration(Java8Parser.ClassBodyDeclarationContext ctx) {
        super.exitClassBodyDeclaration(ctx);
    }

    @Override
    public void enterClassBody(Java8Parser.ClassBodyContext ctx) {
        super.enterClassBody(ctx);
        if (ctx != null && ctx.children != null) {
            for (int i = 0; i < ctx.children.size(); ++i) {
//                System.out.print(ctx.children.get(i).getText()+"\n");
            }
        }
    }

    @Override
    public void exitClassBody(Java8Parser.ClassBodyContext ctx) {
        super.exitClassBody(ctx);
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        super.enterMethodDeclaration(ctx);


    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        super.exitMethodDeclaration(ctx);
    }

    @Override
    public void enterType(Java8Parser.TypeContext ctx) {
        super.enterType(ctx);
    }

    @Override
    public void enterBlock(Java8Parser.BlockContext ctx) {
        super.enterBlock(ctx);
        System.out.println("enterBlock:" + ctx.getText());
    }

    @Override
    public void exitBlock(Java8Parser.BlockContext ctx) {
        super.exitBlock(ctx);
        System.out.println("exitBlock:" + ctx.getText());
    }

    @Override
    public void exitType(Java8Parser.TypeContext ctx) {
        super.exitType(ctx);
    }

    @Override
    public void enterAdditionalBound(Java8Parser.AdditionalBoundContext ctx) {
        super.enterAdditionalBound(ctx);
    }

    @Override
    public void exitAdditionalBound(Java8Parser.AdditionalBoundContext ctx) {
        super.exitAdditionalBound(ctx);
    }

    @Override
    public void enterClassType(Java8Parser.ClassTypeContext ctx) {
        super.enterClassType(ctx);
    }

    @Override
    public void exitClassType(Java8Parser.ClassTypeContext ctx) {
        super.exitClassType(ctx);
    }

    @Override
    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        super.enterClassDeclaration(ctx);
    }

    @Override
    public void enterEnumDeclaration(Java8Parser.EnumDeclarationContext ctx) {
        super.enterEnumDeclaration(ctx);

    }

    @Override
    public void exitEnumDeclaration(Java8Parser.EnumDeclarationContext ctx) {
        super.exitEnumDeclaration(ctx);

    }

    @Override
    public void enterEnumConstantList(Java8Parser.EnumConstantListContext ctx) {
        super.enterEnumConstantList(ctx);

    }

    @Override
    public void exitEnumConstantList(Java8Parser.EnumConstantListContext ctx) {
        super.exitEnumConstantList(ctx);
    }

    @Override
    public void enterNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        super.enterNormalInterfaceDeclaration(ctx);
        System.out.println("enterNormalInterfaceDeclaration:" + ctx.getText());

    }


    @Override
    public void exitNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        super.exitNormalInterfaceDeclaration(ctx);

    }

    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        super.enterNormalClassDeclaration(ctx);
        System.out.println("enterNormalClassDeclaration:" + ctx.getText());

    }


    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        super.exitNormalClassDeclaration(ctx);

    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        super.enterMethodDeclarator(ctx);
    }

    @Override
    public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
        super.enterConstructorDeclaration(ctx);
        System.out.println("enterConstructorDeclaration:" + ctx.getText());
    }

    @Override
    public void enterConstructorBody(Java8Parser.ConstructorBodyContext ctx) {
        super.enterConstructorBody(ctx);
        System.out.println("enterConstructorBody:" + ctx.getText());
    }

    @Override
    public void exitConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
        super.exitConstructorDeclaration(ctx);
    }

    @Override
    public void exitConstructorBody(Java8Parser.ConstructorBodyContext ctx) {
        super.exitConstructorBody(ctx);

    }

    @Override
    public void enterInterfaceMethodDeclaration(Java8Parser.InterfaceMethodDeclarationContext ctx) {
        super.enterInterfaceMethodDeclaration(ctx);
    }

    @Override
    public void enterMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        super.enterMethodHeader(ctx);
        System.out.println("enterMethodHeader:" + ctx.getText());

    }


    @Override
    public void exitMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        super.exitMethodHeader(ctx);
    }

    @Override
    public void enterMethodBody(Java8Parser.MethodBodyContext ctx) {
        super.enterMethodBody(ctx);

        System.out.println("enterMethodBody:" + ctx.getText());
    }

    @Override
    public void exitMethodBody(Java8Parser.MethodBodyContext ctx) {
        super.exitMethodBody(ctx);

    }

    @Override
    public void enterStatementExpression(Java8Parser.StatementExpressionContext ctx) {
        System.out.println("enterStatementExpression:" + ctx.getText());
        super.enterStatementExpression(ctx);
    }

    @Override
    public void exitStatementExpression(Java8Parser.StatementExpressionContext ctx) {
        System.out.println("exitStatementExpression:" + ctx.getText());
        super.exitStatementExpression(ctx);
    }


    @Override
    public void enterFormalParameter(Java8Parser.FormalParameterContext ctx) {
        super.enterFormalParameter(ctx);
    }

    @Override
    public void enterFormalParameterList(Java8Parser.FormalParameterListContext ctx) {
        super.enterFormalParameterList(ctx);
        System.out.println("enterFormalParameterList:" + ctx.getText());


    }


    @Override
    public void enterVariableModifier(Java8Parser.VariableModifierContext ctx) {
        super.enterVariableModifier(ctx);
    }

    @Override
    public void enterClassInstanceCreationExpression(Java8Parser.ClassInstanceCreationExpressionContext ctx) {
        super.enterClassInstanceCreationExpression(ctx);
    }

    @Override
    public void enterClassInstanceCreationExpression_lf_primary(Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {
        super.enterClassInstanceCreationExpression_lf_primary(ctx);
    }

    @Override
    public void enterClassInstanceCreationExpression_lfno_primary(Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {
        super.enterClassInstanceCreationExpression_lfno_primary(ctx);
//        String className = ctx.children.get(1).getText();
//        String classBody = ctx.classBody().getText();
    }

    @Override
    public void enterClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) {
        super.enterClassMemberDeclaration(ctx);
    }

    @Override
    public void exitClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) {
        super.exitClassMemberDeclaration(ctx);
    }

    @Override
    public void enterFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
        super.enterFieldDeclaration(ctx);

    }

    @Override
    public void exitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
        super.exitFieldDeclaration(ctx);
    }

    @Override
    public void enterConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {
        super.enterConstructorDeclarator(ctx);
    }

    @Override
    public void exitConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {
        super.exitConstructorDeclarator(ctx);
    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        System.out.println("enterMethodInvocation" + ctx.getText());
        super.enterMethodInvocation(ctx);

    }

}

