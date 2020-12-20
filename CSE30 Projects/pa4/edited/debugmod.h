/*
 * How to use this module
 * 1 #include this file in your dnsblock.c
 * make calls to debugmod() with one of the commands below
 * if your hashtable is not too badly damaged it will walk the table doing some checks
 * for segfaults you will have to use the debugger
 * 3 link debugmod.o with your code 
 *     gcc -g -Wall dnsblock.c debugmod.o -o dnsblock
 * or gcc -g -Wall dnsblock.o debugmod.o -o dnsblock
 * 
 * example of use in dnsblock.c
 *      debugmod(htable, tabsz, CHECKTAB);
 */

/*
 * include guard
 */
#ifndef DEBUG_MODULE
#define DEBUG_MODULE

/* 
 * command values 
 */ 
#define NODECNT 1  /* print count of nodes in hash table */
#define TABCNT 2   /* print number of empty and chains in table */
#define DUMPTAB 3  /* dump the contents of the database with location in hash table */
#define CHECKTAB 4 /* look for some errors in the table and print info out about them*/

/*
 * prototype
 */
void debugmod(node **htable, unsigned long tabsz, int command);
#endif
