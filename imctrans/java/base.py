import os

import shutil

BASE_FOLDER = os.path.dirname(os.path.abspath(__file__).replace('.pyc', '.py'))


def install(dst_folder):
    assets_folder = os.path.abspath(os.path.join(BASE_FOLDER, 'assets'))
    target_folder = os.path.join(dst_folder)

    # Running from source tree.
    print('*', target_folder)
    if os.path.isdir(assets_folder):
        shutil.rmtree(target_folder, ignore_errors=True)
        shutil.copytree(assets_folder, target_folder, ignore=shutil.ignore_patterns('*generated*'))
        return

    raise RuntimeError("failed to find Java assets folders")
