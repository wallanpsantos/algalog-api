package com.algaworks.algalog.exceptionshandler;

import com.algaworks.algalog.domain.exceptions.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* Anotacao  @ControllerAdvice diz que a classe é um componente do Spring com objetivo de tratar exceções de
 *forma Global para todos os controladores da aplicação
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource; // Interface do Spring para resolver mensagens

    /**
     * Metodo responsavel por mapear a mensagem de erro de uma requisicao Retornado mensagem, campos, data hora e tipo
     * de estatus code
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemaMensagemException problemaMensagemException = ProblemaMensagemException.builder()
                .status(status.value())
                .dataHora(LocalDateTime.now())
                .mensagem("Verifique os campos para a requisição (campo_invalido)")
                .campos(getCamposErrosException(ex))
                .build();

        return handleExceptionInternal(ex, problemaMensagemException, headers, status, request);
    }

    /**
     * Metodo responsavel por mapear os campos que devem conter conteudo obrigatorio na requisicao
     *
     * @param methodArgumentNotValidException
     * @return List<CamposErroException>
     */
    private List<CamposErroException> getCamposErrosException(MethodArgumentNotValidException methodArgumentNotValidException) {

        List<CamposErroException> lista = new ArrayList<>();

        for (ObjectError objError : methodArgumentNotValidException.getBindingResult().getAllErrors()) {
            CamposErroException campo = CamposErroException.builder()
                    .nome(((FieldError) objError).getField())
//                    .mensagem(objError.getDefaultMessage())
                    .mensagem(messageSource.getMessage(objError, LocaleContextHolder.getLocale()))
                    .build();
            lista.add(campo);
        }
        return lista;
    }

    /**
     * Metodo responsavel pelo Exception atrelado globalmente ao NegocioException
     *
     * @param negocioException
     * @param request
     * @return handleExceptionInternal(negocioException, problemaMensagemException, new HttpHeaders (),
     * HttpStatus.BAD_REQUEST, request);
     */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException negocioException, WebRequest request) {

        ProblemaMensagemException problemaMensagemException = ProblemaMensagemException.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .dataHora(LocalDateTime.now())
                .mensagem(negocioException.getMessage())
                .campos(null)
                .build();

        return handleExceptionInternal(negocioException, problemaMensagemException,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
