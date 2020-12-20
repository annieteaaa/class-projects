CC = gcc
CFLAGS = -Wall -g
OBJS = dnsblock.o debugmod.o
PROG = dnsblock
BLOCKFILE = blocklist.txt
OUTFILE = test_file.out
SPECOUT = test_specout.out
DATAFILE = test_file.txt
DBFLAGS = -D DEBUG

$(PROG): $(OBJS)
	$(CC) $(DBFLAGS) $(CFLAGS) $(OBJS) -o $(PROG)

dnsblock.o: dnsblock.c
	$(CC) $(DBFLAGS) $(CFLAGS) -c dnsblock.c

test:	$(PROG)
	./$(PROG) -s -b $(BLOCKFILE) <$(DATAFILE) >$(OUTFILE)
	diff -c $(OUTFILE) $(SPECOUT)
clean:
	rm -f $(PROG) dnsblock.o $(OUTFILE)

