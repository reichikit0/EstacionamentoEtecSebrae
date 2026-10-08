FROM ubuntu:latest
LABEL authors="sl102"

ENTRYPOINT ["top", "-b"]