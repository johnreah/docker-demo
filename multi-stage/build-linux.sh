#============================================================================
# Invoke multi-stage Docker build on Linux. The pushd/popd is a messy hack
# because I wanted to keep the demo files in their own separate subdirectory
# but Docker needs the build context root to be above all the things that are
# being copied into the container (in this case the contents of the java
# directory).
#============================================================================

pushd ..

docker build \
  -f multi-stage/Dockerfile \
  -t demo/api-builder \
  .

popd
