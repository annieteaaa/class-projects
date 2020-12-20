// Describe the Hardware to the assembler
        .arch   armv6
        .cpu    cortex-a53
        .syntax unified
// externs
        .extern putchar
// Constants
        .equ    NULL,   0x0 
        .equ    EOF,    -1 
        .equ    EXIT_SUCCESS, 0   // success return from main
        .equ    EXIT_FAILURE, 1   // error return from main
        .equ    WSTEP,   4        // step of argv

        .text
        .type   main, %function   // main is a function
        .global main              // export main
        .equ    FP_OFFSET, 12     // (saved reg - 1) * 4
main:
        push    {r4, r5, r6, r7, r8, r9, fp, lr}  // saved registers
        add     fp, sp, FP_OFFSET // set frame pointer

        mov     r5, r1            // save argv pointer
        add     r5, r5, WSTEP     // point to argv[1]

        /*  ADD YOUR CODE HERE */

	cmp	r0, #1	// if (r0 == 1) {
	beq	end	// skip to end
			// }

	mov	r7, r1		// r7 = r1
	sub	r0, r0, #1	// r0 = r0 - 1
	mov	r9, WSTEP	// r9 = WSTEP
	mul	r8, r0, r9	// r8 = r0 * r9
	add	r7, r7, r8	// r7 = r7 + r8

	sub	r5, r5, WSTEP	// r5 = r5 - WSTEP

	mov	r6, #1	// r6 = 1

inc:
	add	r5, r5, WSTEP	// r5 = r5 + WSTEP
	ldr	r4, [r5]	// r4 = *r5
	cmp	r4, NULL	// if (r4 == NULL) {
	beq	finish		// break;
				// }
	b	print		// print();

print:				// print() {
				// while (r6 = *r4 != NULL && r6 != EOF) {
	ldrb	r6, [r4]	// .
	cmp	r6, NULL	// .
	beq	space		// .
	cmp	r6, EOF		// .
	beq	space		// .
	cmp	r6, ' '		// if (r6 < ' ') {
	blt	next		// skip to next
				// }
	cmp	r6, '~'		// if (r6 > '~') {
	bgt	next		// skip to next
				// }
	mov	r0, r6		// r0 = r6
	cmp	r0, 'a'		// if (r0 < 'a') {
	blt	nocaps		// .
				// }
	cmp	r0, 'z'		// if (r0 > 'z') {
	bgt	nocaps		// .
				// }
				// else {
	sub	r0, r0, #32	// r0 = r0 - 32
				// }
nocaps:	bl	putchar		// putchar();
next:	add	r4, r4, #1	// r4 = r4 + 1
	b	print		// }
space:	cmp	r7, r5		// if (r7 == r5) {
	beq	finish		// break;
				// }
	mov	r0, ' '		// r0 = ' '
	bl	putchar		// putchar()
	b	inc		// }

finish:	mov	r0, '\n'		// r0 = '\n'
	bl	putchar			// putchar()
end:	mov	r0, EXIT_SUCCESS	// r0 = EXIT_SUCCESS
	bl	done			// .

 done:
        sub     sp, fp, FP_OFFSET // restore stack frame top
        pop     {r4, r5, r6, r7, r8, r9, fp, lr}  // remove stack frame and restore 
        bx      lr                // return to calling function
        .size	main, (. - main)
.end
