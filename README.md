# kbot

A simple golang telegram bot for communication.

## Config / flags
URL to Telegram bot: [t.me/dm_sokolov33Tele_bot](https://t.me/dm_sokolov33Tele_bot) 

tested Go version = 1.19.7

## Basic Installation

Execute the commands to start the bot:

1. git clone https://github.com/FlameFlashy/kbot.git
2. go get 
3. read -s 'TELE_TOKEN' than past token from BotFather in telegram
4. export 'TELE_TOKEN'
3. go build -ldflags "-X="github.com/flameflashy/kbot/cmd.appVersion=1.0.2
4. ./kbot start

Command to use:
/start
/start hello

## Makefile allows you to build code on different platforms. 

With this Makefile, you can specify the `TARGETOS`, `TARGETOSARCH` and `REGESTRY` variables when running the make command. 

For example, to build the code for Linux on an AMD64/ARM64 architecture, and push to Google container registry, you would run:
```bash
make linux TARGETOSARCH=amd64 REGESTRY=HOSTNAME/PROJECT-ID
make linux TARGETOSARCH=arm64 REGESTRY=HOSTNAME/PROJECT-ID

```
For example, to build the code for MacOS on an ARM64/ARM64 architecture, and push to Docker Hub container registry, you would run:
```bash
make macos TARGETOSARCH=amd64 REGESTRY=yourhubusername
make macos TARGETOSARCH=arm64 REGESTRY=yourhubusername
```

For example, to build the code for Windows on an AMD64/ARM64 architecture, you would run:
```bash
make windows TARGETOSARCH=amd64
make windows TARGETOSARCH=arm64
```

> The full list is based on the result of the command `go tool dist list`. The "32-bit/64-bit" information is based on https://golang.org/doc/install/source.
## Support Grid:

|                   | `darwin` |  `linux` | `windows` |                   |
| ----------------: | :------: |  :-----: | :-------: | :---------------- |
| **`amd64`**       |  ✅      | ✅       | ✅         | **`amd64`**      |
| **`arm`**         |          | ✅       | ✅         | **`arm`**        |
| **`arm64`**       | ✅       | ✅       | ✅         | **`arm64`**      |
|  |  **`darwin`** | **`linux`** | **`windows`** |  |