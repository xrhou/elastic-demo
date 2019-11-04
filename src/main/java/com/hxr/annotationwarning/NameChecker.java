package com.hxr.annotationwarning;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;

/**
 * 程序命名规范
 *
 * @author houxiurong
 * @date 2019-09-19
 */
public class NameChecker {

    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    public NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    /**
     * 主要检查java源代码规范
     *
     * @param element 代码元素
     */
    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    /**
     * 名称检查器类,继承 ElementScanner8
     * 将以Vistor模式 访问 抽象语法树中的元素
     */
    private class NameCheckScanner extends ElementScanner8<Void, Void> {

        /**
         * 检查Java类
         *
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(), aVoid);
            checkCameCase(e, true);
            super.visitType(e, aVoid);
            return null;
        }


        /**
         * 检查方法名是否合法
         *
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Diagnostic.Kind.WARNING,
                            "一个普通方法" + name + "不应当与此类名重复，避免与此构造函数产生混淆", e);
                    checkCameCase(e, false);
                }
            }
            super.visitExecutable(e, aVoid);
            return null;
        }


        /**
         * 检查变量名是否合法
         *
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            //如果是枚举类或者常量，按照大写命名检查，否则按照驼峰命名规则检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null
                    || heuristically(e)) {
                checkAllCaps(e);
            } else {
                checkCameCase(e, false);
            }
            return null;
        }

        /**
         * 判断一个变量是否为常量
         *
         * @param e
         * @return
         */
        private boolean heuristically(VariableElement e) {
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                return true;
            } else if (e.getKind() == ElementKind.FIELD
                    && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 检查驼峰
         *
         * @param element
         * @param initialCaps
         */
        private void checkCameCase(Element element, boolean initialCaps) {
            String name = element.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if (!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以小写字母开头", element);
                    return;
                }
            } else if (Character.isLowerCase(firstCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以大写字母开头", element);
                    return;
                }
            } else {
                conventional = false;
                if (conventional) {
                    int cp = firstCodePoint;
                    for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                        cp = name.codePointAt(i);
                        if (Character.isUpperCase(i)) {
                            if (previousUpper) {
                                conventional = false;
                                break;
                            }
                            previousUpper = true;
                        } else {
                            previousUpper = false;
                        }
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "" + "名称" + name + "应当符合驼峰命名法(Camel Case Name)", element);
            }
        }

        /**
         * 大写命名检查
         *
         * @param element 检查代码元素
         */
        private void checkAllCaps(Element element) {
            String name = element.getSimpleName().toString();
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if (!Character.isUpperCase(firstCodePoint)) {
                conventional = false;
            } else {
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int) '_') {
                        if (previousUnderscore) {
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "常量" + name + "应当大写字母或下划线命名，并且以字母开头", element);
            }
        }

    }
}
