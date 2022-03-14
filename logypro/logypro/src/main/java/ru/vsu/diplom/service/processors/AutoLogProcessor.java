package ru.vsu.diplom.service.processors;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("ru.vsu.diplom.service.processors.AutoLog")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AutoLogProcessor extends AbstractProcessor {

//    private final Specifier specifier;
    private ProcessingEnvironment processingEnv;
    @Override
    public synchronized void init(ProcessingEnvironment env){
        processingEnv = env;
        super.init(env);
    }

    @Override
    public boolean process(Set <? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set <? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            List <Element> matched = annotatedElements.stream()
                    .map(TypeElement.class::cast)
//                    .filter(specifier::specify)
                    .collect(Collectors.toList());

            matched.forEach(x -> System.out.println(x.getSimpleName()));
            try {
                JavaFileObject f = processingEnv.getFiler().createSourceFile("TestAutogenerate");
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Creating " + f.toUri());
                Writer w = f.openWriter();
                try {
                    PrintWriter pw = new PrintWriter(w);
                    pw.println("package " + annotatedElements.getClass().getPackage().getName() + ";");
                    pw.println("\npublic class "  + "TestAutogenerate {");
                    pw.println("}");
                    pw.flush();
                } finally {
                    w.close();
                }
            } catch (IOException x) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, x.toString());
            }
        }
        return true;
    }
}
