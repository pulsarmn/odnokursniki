package org.odnokursniki.auth.util;


/**
 * An interface that defines operations for code generation
 *
 * @since 0.1.0
 * @author pulsar
 */
public interface CodeGenerator {

    /**
     * Generates some code and returns it
     * @return non {@code null} code
     */
    String generate();
}
