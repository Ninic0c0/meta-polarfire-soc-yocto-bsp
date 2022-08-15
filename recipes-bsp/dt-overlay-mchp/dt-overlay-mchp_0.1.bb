SUMMARY = "Device Trees overlay for BSPs"
DESCRIPTION = "Device Tree overlay generation and packaging for BSP"
SECTION = "bsp"

LICENSE = "MIT & GPLv2"
LIC_FILES_CHKSUM = " \
		file://LICENSES/MIT;md5=e8f57dd048e186199433be2c41bd3d6d \
		file://LICENSES/GPL-2.0;md5=e6a75371ba4d16749254a51215d13f97 \
		"
PR = "r0"

inherit devicetree

COMPATIBLE_MACHINE = "(icicle-kit-es|icicle-kit-es-amp|m100pfsev|sev-kit-es)"

S = "${WORKDIR}/git"

DT_FILES_PATH:icicle-kit-es = "${WORKDIR}/git/mpfs_icicle"
DT_FILES_PATH:icicle-kit-es-amp = "${WORKDIR}/git/mpfs_icicle"

BRANCH = "feature/mpfs_qspi"
SRCREV="${AUTOREV}"
SRC_URI="git://bitbucket.microchip.com/scm/fpga_pfsoc_es/dt-overlay-polarfire-soc.git;protocol=https;branch=${BRANCH} \
"

do_install() {
    cd ${B}
    for DTB_FILE in `ls *.dtbo`; do
        install -Dm 0644 ${B}/${DTB_FILE} ${D}/boot/${DTB_FILE}
    done
}

do_deploy() {
    cd ${B}
    for DTB_FILE in `ls *.dtbo`; do
        install -Dm 0644 ${B}/${DTB_FILE} ${DEPLOYDIR}/overlays/${DTB_FILE}
    done
}

FILES:${PN} += "/boot/*.dtbo"
