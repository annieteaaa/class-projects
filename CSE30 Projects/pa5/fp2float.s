// Describe the Hardware to the assembler
        .arch   armv6
        .cpu    cortex-a53
        .syntax unified

        .text                       // start of the text segment

        /////////////////////////////////////////////////////////
        // function FP2float
        /////////////////////////////////////////////////////////

        .type   FP2float, %function // define as a function
        .global FP2float            // export function name
        .equ    FP_OFF_FP2, 28      // (regs - 1) * 4

FP2float:
        push    {r4-r9, fp, lr}     // use r4-r9 for variables
        add     fp, sp, FP_OFF_FP2  // locate our frame pointer
        
        /////////////////////////////////////////////////////////
        // do not edit anything in this function above this line
        // your code goes Below this comment
        // Store return value (results) in r0
        /////////////////////////////////////////////////////////


	and	r4, r0, #0x7f	// r4 = r0 & 0x7f
	cmp	r4, #0x00	// if(r4 == 0) {
	beq	zero		// .
				// }

				// else {
	mov	r5, #0x00000000	// r5 = 0x0
	add	r5, r5, r0	// r5 = r5 + r0
	lsl	r5, r5, #24	// r5 = r5 << 24
	asr	r5, r5, #5	// r5 = r5 >> 5 (sign extend)

	and	r6, r5, #0x03800000	// r6 = r5 & 0x03800000
	add	r6, r6, #0x3e000000	// r6 = r6 & 0x3e000000
	ldr	r7, =0x807fffff		// r7 = 0x807fffff
	and	r5, r5, r7		// r5 = r5 + r7
	add	r5, r5, r6		// r5 = r5 + r6

	mov	r0, r5	// r0 = r5
	b	done	// .

zero:	bl	zeroFP2float	// .
done:				// }

        /////////////////////////////////////////////////////////
        // your code goes ABOVE this comment
        // do not edit anything in this function below this line
        /////////////////////////////////////////////////////////

        sub     sp, fp, FP_OFF_FP2  // restore sp
        pop     {r4-r9,fp, lr}      // restore saved registers
        bx      lr                  // function return 
        .size   FP2float,(. - FP2float)


        /////////////////////////////////////////////////////////
        // function zeroFP2float
        /////////////////////////////////////////////////////////

        .type   zeroFP2float, %function // define as a function
        .global FP2float                // export function name
        .equ    FP_OFF_ZER, 4           // (regs - 1) * 4

zeroFP2float:
        push    {fp, lr}            // use r0-r3 for variables
        add     fp, sp, FP_OFF_ZER  // locate our frame pointer
        
        /////////////////////////////////////////////////////////
        // do not edit anything in this function above this line
        // your code goes Below this comment
        // Store return value (results) in r0
        /////////////////////////////////////////////////////////

	mov	r3, #0x00000000		// r3 = 0x0
	add	r3, r3, r0		// r3 = r3 + r0
	lsl	r3, r3, #24		// r3 = r3 << 24
	and	r3, r3, #0x80000000	// r3 = r3 + 0x80000000
	mov	r0, r3			// r0 = r3


        /////////////////////////////////////////////////////////
        // your code goes ABOVE this comment
        // do not edit anything in this function below this line
        /////////////////////////////////////////////////////////

        sub     sp, fp, FP_OFF_ZER  // restore sp
        pop     {fp, lr}            // restore saved registers
        bx      lr                  // function return
        .size   zeroFP2float,(. - zeroFP2float)

.end
