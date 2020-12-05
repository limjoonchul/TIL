package com.rubypaper.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

// around�� ����ó���� ����ó���� ��� �� �� �մ�. ������ filter�� ������ ����
@Service // ���� ���Ͼ��ϰ� �ֳ����̼� ������� �����.
@Aspect
// ���̵� �ʿ����? xml�� �����ϱ� ���ؼ� �ʿ��ߴ� ���̴�. 
public class AroundAdvice {
	
	// ���ǻ��� joinpoint�� ���� ���������� �ֳ��ϸ� ��������Ʈ���� proceed�޼ҵ尡 ���⶧����
	// ProceedingJoinPoint�� joinpoint�� �ڽ� Ŭ�����̴�. �׷��� around�� ProceedingJoinPoint�ؾ߸� �Ѵ�.
	// �ٵ� logadciec���� ProceedingJoinPoint�̰� ����ϸ� ������ ���� ������ around������ ��밡���ϴ�.
	@Around("BoardPointcut.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {  
		String method = jp.getSignature().getName(); // Ŭ���̾�Ʈ�� ȣ���� �޼ҵ� �̸�
		
		Object obj = null;
//		System.out.println("----Before Logic -----");
		StopWatch watch = new StopWatch();
		watch.start();
		
		obj = jp.proceed();
		
//		System.out.println("----After Logic -----");
		watch.stop();
		
		System.out.println(method + "() �޼ҵ� ���࿡ �ҿ�� �ð� : " + watch.getTotalTimeMillis() + "(ms)��");
		return obj;
	}
	// ����Ÿ���� ���� void�̸� �ȵȴ�. �Ű������� ������ �ִ�. �ִ� ����Ͻ������� ���� ����ó�� ����ó���̴�.
	// ���� around�� void�� ���� �� ���� ���� ó���� �ϴµ� proceed�� ������ ���ϰ��� �ִ� �޼ҵ带 �����Ű�� ��ȯ ���� �޾Ƽ� aroundLog�� �޾ƿ͵�
	// ���⼭ return�� ���� ���� ���� ������ ���ܰ� �߻��ϰ� ȭ�鿡 ����� ���� ��������.

}
