import roadmapsData from "@/data/roadmaps.json";
import RoadmapClient from "./RoadmapClient";

export function generateStaticParams() {
  return roadmapsData.map((roadmap) => ({
    slug: roadmap.slug,
  }));
}

export default async function DynamicRoadmapScreen({ params }: { params: Promise<{ slug: string }> }) {
  const { slug } = await params;
  return <RoadmapClient slug={slug} />;
}
